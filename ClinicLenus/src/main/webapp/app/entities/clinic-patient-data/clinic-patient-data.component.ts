import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { ClinicPatientData } from './clinic-patient-data.model';
import { ClinicPatientDataService } from './clinic-patient-data.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-clinic-patient-data',
    templateUrl: './clinic-patient-data.component.html'
})
export class ClinicPatientDataComponent implements OnInit, OnDestroy {
clinicPatientData: ClinicPatientData[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private clinicPatientDataService: ClinicPatientDataService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.clinicPatientDataService.query().subscribe(
            (res: ResponseWrapper) => {
                this.clinicPatientData = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInClinicPatientData();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ClinicPatientData) {
        return item.id;
    }
    registerChangeInClinicPatientData() {
        this.eventSubscriber = this.eventManager.subscribe('clinicPatientDataListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
