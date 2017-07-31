import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ClinicPatientData } from './clinic-patient-data.model';
import { ClinicPatientDataService } from './clinic-patient-data.service';

@Injectable()
export class ClinicPatientDataPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private clinicPatientDataService: ClinicPatientDataService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.clinicPatientDataService.find(id).subscribe((clinicPatientData) => {
                if (clinicPatientData.date) {
                    clinicPatientData.date = {
                        year: clinicPatientData.date.getFullYear(),
                        month: clinicPatientData.date.getMonth() + 1,
                        day: clinicPatientData.date.getDate()
                    };
                }
                this.clinicPatientDataModalRef(component, clinicPatientData);
            });
        } else {
            return this.clinicPatientDataModalRef(component, new ClinicPatientData());
        }
    }

    clinicPatientDataModalRef(component: Component, clinicPatientData: ClinicPatientData): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.clinicPatientData = clinicPatientData;
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
