import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Prescription } from './prescription.model';
import { PrescriptionService } from './prescription.service';

@Injectable()
export class PrescriptionPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private prescriptionService: PrescriptionService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.prescriptionService.find(id).subscribe((prescription) => {
                if (prescription.date) {
                    prescription.date = {
                        year: prescription.date.getFullYear(),
                        month: prescription.date.getMonth() + 1,
                        day: prescription.date.getDate()
                    };
                }
                this.prescriptionModalRef(component, prescription);
            });
        } else {
            return this.prescriptionModalRef(component, new Prescription());
        }
    }

    prescriptionModalRef(component: Component, prescription: Prescription): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.prescription = prescription;
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
