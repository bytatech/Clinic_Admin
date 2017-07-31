import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { ClinicOwnerInfo } from './clinic-owner-info.model';
import { ClinicOwnerInfoService } from './clinic-owner-info.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-clinic-owner-info',
    templateUrl: './clinic-owner-info.component.html'
})
export class ClinicOwnerInfoComponent implements OnInit, OnDestroy {
clinicOwnerInfos: ClinicOwnerInfo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private clinicOwnerInfoService: ClinicOwnerInfoService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.clinicOwnerInfoService.query().subscribe(
            (res: ResponseWrapper) => {
                this.clinicOwnerInfos = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInClinicOwnerInfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ClinicOwnerInfo) {
        return item.id;
    }
    registerChangeInClinicOwnerInfos() {
        this.eventSubscriber = this.eventManager.subscribe('clinicOwnerInfoListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
