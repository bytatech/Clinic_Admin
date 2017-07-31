import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { Governtment } from './governtment.model';
import { GoverntmentService } from './governtment.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-governtment',
    templateUrl: './governtment.component.html'
})
export class GoverntmentComponent implements OnInit, OnDestroy {
governtments: Governtment[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private governtmentService: GoverntmentService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.governtmentService.query().subscribe(
            (res: ResponseWrapper) => {
                this.governtments = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInGoverntments();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Governtment) {
        return item.id;
    }
    registerChangeInGoverntments() {
        this.eventSubscriber = this.eventManager.subscribe('governtmentListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
