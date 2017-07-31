import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ClinicDetails } from './clinic-details.model';
import { ClinicDetailsService } from './clinic-details.service';

@Injectable()
export class ClinicDetailsPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private clinicDetailsService: ClinicDetailsService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.clinicDetailsService.find(id).subscribe((clinicDetails) => {
                if (clinicDetails.clinicTiming) {
                    clinicDetails.clinicTiming = {
                        year: clinicDetails.clinicTiming.getFullYear(),
                        month: clinicDetails.clinicTiming.getMonth() + 1,
                        day: clinicDetails.clinicTiming.getDate()
                    };
                }
                this.clinicDetailsModalRef(component, clinicDetails);
            });
        } else {
            return this.clinicDetailsModalRef(component, new ClinicDetails());
        }
    }

    clinicDetailsModalRef(component: Component, clinicDetails: ClinicDetails): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.clinicDetails = clinicDetails;
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
