import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { ClinicPatientPrivateData } from './clinic-patient-private-data.model';
import { ClinicPatientPrivateDataService } from './clinic-patient-private-data.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-clinic-patient-private-data',
    templateUrl: './clinic-patient-private-data.component.html'
})
export class ClinicPatientPrivateDataComponent implements OnInit, OnDestroy {
clinicPatientPrivateData: ClinicPatientPrivateData[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private clinicPatientPrivateDataService: ClinicPatientPrivateDataService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.clinicPatientPrivateDataService.query().subscribe(
            (res: ResponseWrapper) => {
                this.clinicPatientPrivateData = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInClinicPatientPrivateData();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ClinicPatientPrivateData) {
        return item.id;
    }
    registerChangeInClinicPatientPrivateData() {
        this.eventSubscriber = this.eventManager.subscribe('clinicPatientPrivateDataListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
