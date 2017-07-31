import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { MedicineSystem } from './medicine-system.model';
import { MedicineSystemPopupService } from './medicine-system-popup.service';
import { MedicineSystemService } from './medicine-system.service';

@Component({
    selector: 'jhi-medicine-system-dialog',
    templateUrl: './medicine-system-dialog.component.html'
})
export class MedicineSystemDialogComponent implements OnInit {

    medicineSystem: MedicineSystem;
    authorities: any[];
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private medicineSystemService: MedicineSystemService,
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
        if (this.medicineSystem.id !== undefined) {
            this.subscribeToSaveResponse(
                this.medicineSystemService.update(this.medicineSystem));
        } else {
            this.subscribeToSaveResponse(
                this.medicineSystemService.create(this.medicineSystem));
        }
    }

    private subscribeToSaveResponse(result: Observable<MedicineSystem>) {
        result.subscribe((res: MedicineSystem) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: MedicineSystem) {
        this.eventManager.broadcast({ name: 'medicineSystemListModification', content: 'OK'});
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
    selector: 'jhi-medicine-system-popup',
    template: ''
})
export class MedicineSystemPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private medicineSystemPopupService: MedicineSystemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.medicineSystemPopupService
                    .open(MedicineSystemDialogComponent, params['id']);
            } else {
                this.modalRef = this.medicineSystemPopupService
                    .open(MedicineSystemDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
