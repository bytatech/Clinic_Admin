import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiLanguageService, JhiAlertService } from 'ng-jhipster';

import { MedicineSystem } from './medicine-system.model';
import { MedicineSystemService } from './medicine-system.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-medicine-system',
    templateUrl: './medicine-system.component.html'
})
export class MedicineSystemComponent implements OnInit, OnDestroy {
medicineSystems: MedicineSystem[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private medicineSystemService: MedicineSystemService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.medicineSystemService.query().subscribe(
            (res: ResponseWrapper) => {
                this.medicineSystems = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInMedicineSystems();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: MedicineSystem) {
        return item.id;
    }
    registerChangeInMedicineSystems() {
        this.eventSubscriber = this.eventManager.subscribe('medicineSystemListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
