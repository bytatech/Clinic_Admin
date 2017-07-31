import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Drugs } from './drugs.model';
import { DrugsPopupService } from './drugs-popup.service';
import { DrugsService } from './drugs.service';
import { Prescription, PrescriptionService } from '../prescription';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-drugs-dialog',
    templateUrl: './drugs-dialog.component.html'
})
export class DrugsDialogComponent implements OnInit {

    drugs: Drugs;
    authorities: any[];
    isSaving: boolean;

    prescriptions: Prescription[];

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private drugsService: DrugsService,
        private prescriptionService: PrescriptionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.prescriptionService.query()
            .subscribe((res: ResponseWrapper) => { this.prescriptions = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.drugs.id !== undefined) {
            this.subscribeToSaveResponse(
                this.drugsService.update(this.drugs));
        } else {
            this.subscribeToSaveResponse(
                this.drugsService.create(this.drugs));
        }
    }

    private subscribeToSaveResponse(result: Observable<Drugs>) {
        result.subscribe((res: Drugs) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: Drugs) {
        this.eventManager.broadcast({ name: 'drugsListModification', content: 'OK'});
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

    trackPrescriptionById(index: number, item: Prescription) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-drugs-popup',
    template: ''
})
export class DrugsPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private drugsPopupService: DrugsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.drugsPopupService
                    .open(DrugsDialogComponent, params['id']);
            } else {
                this.modalRef = this.drugsPopupService
                    .open(DrugsDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
