import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ClinicDetails } from './clinic-details.model';
import { ClinicDetailsPopupService } from './clinic-details-popup.service';
import { ClinicDetailsService } from './clinic-details.service';

@Component({
    selector: 'jhi-clinic-details-delete-dialog',
    templateUrl: './clinic-details-delete-dialog.component.html'
})
export class ClinicDetailsDeleteDialogComponent {

    clinicDetails: ClinicDetails;

    constructor(
        private clinicDetailsService: ClinicDetailsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.clinicDetailsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'clinicDetailsListModification',
                content: 'Deleted an clinicDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-clinic-details-delete-popup',
    template: ''
})
export class ClinicDetailsDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private clinicDetailsPopupService: ClinicDetailsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.clinicDetailsPopupService
                .open(ClinicDetailsDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
