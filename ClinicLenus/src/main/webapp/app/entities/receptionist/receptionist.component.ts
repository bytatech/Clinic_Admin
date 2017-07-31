import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { Receptionist } from './receptionist.model';
import { ReceptionistService } from './receptionist.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-receptionist',
    templateUrl: './receptionist.component.html'
})
export class ReceptionistComponent implements OnInit, OnDestroy {
receptionists: Receptionist[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private receptionistService: ReceptionistService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.receptionistService.query().subscribe(
            (res: ResponseWrapper) => {
                this.receptionists = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInReceptionists();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Receptionist) {
        return item.id;
    }
    registerChangeInReceptionists() {
        this.eventSubscriber = this.eventManager.subscribe('receptionistListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
