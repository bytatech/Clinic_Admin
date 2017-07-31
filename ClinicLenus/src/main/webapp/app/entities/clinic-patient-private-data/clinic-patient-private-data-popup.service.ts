import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ClinicPatientPrivateData } from './clinic-patient-private-data.model';
import { ClinicPatientPrivateDataService } from './clinic-patient-private-data.service';

@Injectable()
export class ClinicPatientPrivateDataPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private clinicPatientPrivateDataService: ClinicPatientPrivateDataService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.clinicPatientPrivateDataService.find(id).subscribe((clinicPatientPrivateData) => {
                this.clinicPatientPrivateDataModalRef(component, clinicPatientPrivateData);
            });
        } else {
            return this.clinicPatientPrivateDataModalRef(component, new ClinicPatientPrivateData());
        }
    }

    clinicPatientPrivateDataModalRef(component: Component, clinicPatientPrivateData: ClinicPatientPrivateData): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.clinicPatientPrivateData = clinicPatientPrivateData;
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
