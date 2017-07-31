import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { Drugs } from './drugs.model';
import { DrugsService } from './drugs.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-drugs',
    templateUrl: './drugs.component.html'
})
export class DrugsComponent implements OnInit, OnDestroy {
drugs: Drugs[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private drugsService: DrugsService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.drugsService.query().subscribe(
            (res: ResponseWrapper) => {
                this.drugs = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInDrugs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Drugs) {
        return item.id;
    }
    registerChangeInDrugs() {
        this.eventSubscriber = this.eventManager.subscribe('drugsListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
