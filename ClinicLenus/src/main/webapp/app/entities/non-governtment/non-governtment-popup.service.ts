import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { NonGoverntment } from './non-governtment.model';
import { NonGoverntmentService } from './non-governtment.service';

@Injectable()
export class NonGoverntmentPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private nonGoverntmentService: NonGoverntmentService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.nonGoverntmentService.find(id).subscribe((nonGoverntment) => {
                this.nonGoverntmentModalRef(component, nonGoverntment);
            });
        } else {
            return this.nonGoverntmentModalRef(component, new NonGoverntment());
        }
    }

    nonGoverntmentModalRef(component: Component, nonGoverntment: NonGoverntment): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.nonGoverntment = nonGoverntment;
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
