import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ClinicOwnerInfo } from './clinic-owner-info.model';
import { ClinicOwnerInfoPopupService } from './clinic-owner-info-popup.service';
import { ClinicOwnerInfoService } from './clinic-owner-info.service';

@Component({
    selector: 'jhi-clinic-owner-info-delete-dialog',
    templateUrl: './clinic-owner-info-delete-dialog.component.html'
})
export class ClinicOwnerInfoDeleteDialogComponent {

    clinicOwnerInfo: ClinicOwnerInfo;

    constructor(
        private clinicOwnerInfoService: ClinicOwnerInfoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.clinicOwnerInfoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'clinicOwnerInfoListModification',
                content: 'Deleted an clinicOwnerInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-clinic-owner-info-delete-popup',
    template: ''
})
export class ClinicOwnerInfoDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private clinicOwnerInfoPopupService: ClinicOwnerInfoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.clinicOwnerInfoPopupService
                .open(ClinicOwnerInfoDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
