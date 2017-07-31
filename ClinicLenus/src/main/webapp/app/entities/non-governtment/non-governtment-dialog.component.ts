import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { NonGoverntment } from './non-governtment.model';
import { NonGoverntmentPopupService } from './non-governtment-popup.service';
import { NonGoverntmentService } from './non-governtment.service';

@Component({
    selector: 'jhi-non-governtment-dialog',
    templateUrl: './non-governtment-dialog.component.html'
})
export class NonGoverntmentDialogComponent implements OnInit {

    nonGoverntment: NonGoverntment;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private nonGoverntmentService: NonGoverntmentService,
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
        if (this.nonGoverntment.id !== undefined) {
            this.subscribeToSaveResponse(
                this.nonGoverntmentService.update(this.nonGoverntment));
        } else {
            this.subscribeToSaveResponse(
                this.nonGoverntmentService.create(this.nonGoverntment));
        }
    }

    private subscribeToSaveResponse(result: Observable<NonGoverntment>) {
        result.subscribe((res: NonGoverntment) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: NonGoverntment) {
        this.eventManager.broadcast({ name: 'nonGoverntmentListModification', content: 'OK'});
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
    selector: 'jhi-non-governtment-popup',
    template: ''
})
export class NonGoverntmentPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private nonGoverntmentPopupService: NonGoverntmentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.nonGoverntmentPopupService
                    .open(NonGoverntmentDialogComponent, params['id']);
            } else {
                this.modalRef = this.nonGoverntmentPopupService
                    .open(NonGoverntmentDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
