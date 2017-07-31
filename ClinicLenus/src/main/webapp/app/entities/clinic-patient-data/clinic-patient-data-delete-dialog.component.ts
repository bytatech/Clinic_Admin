import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ClinicPatientData } from './clinic-patient-data.model';
import { ClinicPatientDataPopupService } from './clinic-patient-data-popup.service';
import { ClinicPatientDataService } from './clinic-patient-data.service';

@Component({
    selector: 'jhi-clinic-patient-data-delete-dialog',
    templateUrl: './clinic-patient-data-delete-dialog.component.html'
})
export class ClinicPatientDataDeleteDialogComponent {

    clinicPatientData: ClinicPatientData;

    constructor(
        private clinicPatientDataService: ClinicPatientDataService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.clinicPatientDataService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'clinicPatientDataListModification',
                content: 'Deleted an clinicPatientData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-clinic-patient-data-delete-popup',
    template: ''
})
export class ClinicPatientDataDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private clinicPatientDataPopupService: ClinicPatientDataPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.clinicPatientDataPopupService
                .open(ClinicPatientDataDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
