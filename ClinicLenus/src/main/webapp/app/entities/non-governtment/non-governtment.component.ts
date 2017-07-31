import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { NonGoverntment } from './non-governtment.model';
import { NonGoverntmentService } from './non-governtment.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-non-governtment',
    templateUrl: './non-governtment.component.html'
})
export class NonGoverntmentComponent implements OnInit, OnDestroy {
nonGoverntments: NonGoverntment[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private nonGoverntmentService: NonGoverntmentService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.nonGoverntmentService.query().subscribe(
            (res: ResponseWrapper) => {
                this.nonGoverntments = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInNonGoverntments();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: NonGoverntment) {
        return item.id;
    }
    registerChangeInNonGoverntments() {
        this.eventSubscriber = this.eventManager.subscribe('nonGoverntmentListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
