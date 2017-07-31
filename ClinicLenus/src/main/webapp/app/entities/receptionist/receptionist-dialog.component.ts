import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Receptionist } from './receptionist.model';
import { ReceptionistPopupService } from './receptionist-popup.service';
import { ReceptionistService } from './receptionist.service';

@Component({
    selector: 'jhi-receptionist-dialog',
    templateUrl: './receptionist-dialog.component.html'
})
export class ReceptionistDialogComponent implements OnInit {

    receptionist: Receptionist;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private receptionistService: ReceptionistService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.receptionist.id !== undefined) {
            this.subscribeToSaveResponse(
                this.receptionistService.update(this.receptionist));
        } else {
            this.subscribeToSaveResponse(
                this.receptionistService.create(this.receptionist));
        }
    }

    private subscribeToSaveResponse(result: Observable<Receptionist>) {
        result.subscribe((res: Receptionist) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Receptionist) {
        this.eventManager.broadcast({ name: 'receptionistListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-receptionist-popup',
    template: ''
})
export class ReceptionistPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private receptionistPopupService: ReceptionistPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.receptionistPopupService
                    .open(ReceptionistDialogComponent, params['id']);
            } else {
                this.modalRef = this.receptionistPopupService
                    .open(ReceptionistDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
