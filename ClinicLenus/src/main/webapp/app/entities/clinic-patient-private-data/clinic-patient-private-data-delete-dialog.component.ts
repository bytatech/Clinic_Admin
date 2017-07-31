import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ClinicPatientPrivateData } from './clinic-patient-private-data.model';
import { ClinicPatientPrivateDataPopupService } from './clinic-patient-private-data-popup.service';
import { ClinicPatientPrivateDataService } from './clinic-patient-private-data.service';

@Component({
    selector: 'jhi-clinic-patient-private-data-delete-dialog',
    templateUrl: './clinic-patient-private-data-delete-dialog.component.html'
})
export class ClinicPatientPrivateDataDeleteDialogComponent {

    clinicPatientPrivateData: ClinicPatientPrivateData;

    constructor(
        private clinicPatientPrivateDataService: ClinicPatientPrivateDataService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.clinicPatientPrivateDataService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'clinicPatientPrivateDataListModification',
                content: 'Deleted an clinicPatientPrivateData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-clinic-patient-private-data-delete-popup',
    template: ''
})
export class ClinicPatientPrivateDataDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private clinicPatientPrivateDataPopupService: ClinicPatientPrivateDataPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.clinicPatientPrivateDataPopupService
                .open(ClinicPatientPrivateDataDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
