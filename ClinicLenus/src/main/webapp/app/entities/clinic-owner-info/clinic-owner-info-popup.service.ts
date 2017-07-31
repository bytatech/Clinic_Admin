import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ClinicOwnerInfo } from './clinic-owner-info.model';
import { ClinicOwnerInfoService } from './clinic-owner-info.service';

@Injectable()
export class ClinicOwnerInfoPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private clinicOwnerInfoService: ClinicOwnerInfoService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.clinicOwnerInfoService.find(id).subscribe((clinicOwnerInfo) => {
                this.clinicOwnerInfoModalRef(component, clinicOwnerInfo);
            });
        } else {
            return this.clinicOwnerInfoModalRef(component, new ClinicOwnerInfo());
        }
    }

    clinicOwnerInfoModalRef(component: Component, clinicOwnerInfo: ClinicOwnerInfo): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.clinicOwnerInfo = clinicOwnerInfo;
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
