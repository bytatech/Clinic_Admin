import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ClinicDetails } from './clinic-details.model';
import { ClinicDetailsPopupService } from './clinic-details-popup.service';
import { ClinicDetailsService } from './clinic-details.service';
import { ClinicOwnerInfo, ClinicOwnerInfoService } from '../clinic-owner-info';
import { Governtment, GoverntmentService } from '../governtment';
import { NonGoverntment, NonGoverntmentService } from '../non-governtment';
import { MedicineSystem, MedicineSystemService } from '../medicine-system';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-clinic-details-dialog',
    templateUrl: './clinic-details-dialog.component.html'
})
export class ClinicDetailsDialogComponent implements OnInit {

    clinicDetails: ClinicDetails;
    authorities: any[];
    isSaving: boolean;

    clinicownerinfos: ClinicOwnerInfo[];

    governtments: Governtment[];

    nongoverntments: NonGoverntment[];

    medicinesystems: MedicineSystem[];
    clinicTimingDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private alertService: JhiAlertService,
        private clinicDetailsService: ClinicDetailsService,
        private clinicOwnerInfoService: ClinicOwnerInfoService,
        private governtmentService: GoverntmentService,
        private nonGoverntmentService: NonGoverntmentService,
        private medicineSystemService: MedicineSystemService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.clinicOwnerInfoService
            .query({filter: 'clinicdetails-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.clinicDetails.clinicOwnerInfo || !this.clinicDetails.clinicOwnerInfo.id) {
                    this.clinicownerinfos = res.json;
                } else {
                    this.clinicOwnerInfoService
                        .find(this.clinicDetails.clinicOwnerInfo.id)
                        .subscribe((subRes: ClinicOwnerInfo) => {
                            this.clinicownerinfos = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.governtmentService
            .query({filter: 'clinicdetails-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.clinicDetails.governtment || !this.clinicDetails.governtment.id) {
                    this.governtments = res.json;
                } else {
                    this.governtmentService
                        .find(this.clinicDetails.governtment.id)
                        .subscribe((subRes: Governtment) => {
                            this.governtments = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.nonGoverntmentService
            .query({filter: 'clinicdetails-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.clinicDetails.nonGoverntment || !this.clinicDetails.nonGoverntment.id) {
                    this.nongoverntments = res.json;
                } else {
                    this.nonGoverntmentService
                        .find(this.clinicDetails.nonGoverntment.id)
                        .subscribe((subRes: NonGoverntment) => {
                            this.nongoverntments = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
        this.medicineSystemService
            .query({filter: 'clinicdetails-is-null'})
            .subscribe((res: ResponseWrapper) => {
                if (!this.clinicDetails.medicineSystem || !this.clinicDetails.medicineSystem.id) {
                    this.medicinesystems = res.json;
                } else {
                    this.medicineSystemService
                        .find(this.clinicDetails.medicineSystem.id)
                        .subscribe((subRes: MedicineSystem) => {
                            this.medicinesystems = [subRes].concat(res.json);
                        }, (subRes: ResponseWrapper) => this.onError(subRes.json));
                }
            }, (res: ResponseWrapper) => this.onError(res.json));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, clinicDetails, field, isImage) {
        if (event && event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            if (isImage && !/^image\//.test(file.type)) {
                return;
            }
            this.dataUtils.toBase64(file, (base64Data) => {
                clinicDetails[field] = base64Data;
                clinicDetails[`${field}ContentType`] = file.type;
            });
        }
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.clinicDetails, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.clinicDetails.id !== undefined) {
            this.subscribeToSaveResponse(
                this.clinicDetailsService.update(this.clinicDetails));
        } else {
            this.subscribeToSaveResponse(
                this.clinicDetailsService.create(this.clinicDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<ClinicDetails>) {
        result.subscribe((res: ClinicDetails) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: ClinicDetails) {
        this.eventManager.broadcast({ name: 'clinicDetailsListModification', content: 'OK'});
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

    trackClinicOwnerInfoById(index: number, item: ClinicOwnerInfo) {
        return item.id;
    }

    trackGoverntmentById(index: number, item: Governtment) {
        return item.id;
    }

    trackNonGoverntmentById(index: number, item: NonGoverntment) {
        return item.id;
    }

    trackMedicineSystemById(index: number, item: MedicineSystem) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-clinic-details-popup',
    template: ''
})
export class ClinicDetailsPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private clinicDetailsPopupService: ClinicDetailsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.clinicDetailsPopupService
                    .open(ClinicDetailsDialogComponent, params['id']);
            } else {
                this.modalRef = this.clinicDetailsPopupService
                    .open(ClinicDetailsDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
