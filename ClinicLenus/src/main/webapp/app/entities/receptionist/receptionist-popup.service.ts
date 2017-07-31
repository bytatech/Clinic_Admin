import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Receptionist } from './receptionist.model';
import { ReceptionistService } from './receptionist.service';

@Injectable()
export class ReceptionistPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private receptionistService: ReceptionistService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.receptionistService.find(id).subscribe((receptionist) => {
                this.receptionistModalRef(component, receptionist);
            });
        } else {
            return this.receptionistModalRef(component, new Receptionist());
        }
    }

    receptionistModalRef(component: Component, receptionist: Receptionist): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.receptionist = receptionist;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
