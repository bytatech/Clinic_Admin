import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Governtment } from './governtment.model';
import { GoverntmentPopupService } from './governtment-popup.service';
import { GoverntmentService } from './governtment.service';

@Component({
    selector: 'jhi-governtment-dialog',
    templateUrl: './governtment-dialog.component.html'
})
export class GoverntmentDialogComponent implements OnInit {

    governtment: Governtment;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private governtmentService: GoverntmentService,
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
        if (this.governtment.id !== undefined) {
            this.subscribeToSaveResponse(
                this.governtmentService.update(this.governtment));
        } else {
            this.subscribeToSaveResponse(
                this.governtmentService.create(this.governtment));
        }
    }

    private subscribeToSaveResponse(result: Observable<Governtment>) {
        result.subscribe((res: Governtment) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Governtment) {
        this.eventManager.broadcast({ name: 'governtmentListModification', content: 'OK'});
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
    selector: 'jhi-governtment-popup',
    template: ''
})
export class GoverntmentPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private governtmentPopupService: GoverntmentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.governtmentPopupService
                    .open(GoverntmentDialogComponent, params['id']);
            } else {
                this.modalRef = this.governtmentPopupService
                    .open(GoverntmentDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
