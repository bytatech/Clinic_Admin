import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { ClinicOwnerInfo } from './clinic-owner-info.model';
import { ClinicOwnerInfoService } from './clinic-owner-info.service';

@Component({
    selector: 'jhi-clinic-owner-info-detail',
    templateUrl: './clinic-owner-info-detail.component.html'
})
export class ClinicOwnerInfoDetailComponent implements OnInit, OnDestroy {

    clinicOwnerInfo: ClinicOwnerInfo;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private clinicOwnerInfoService: ClinicOwnerInfoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInClinicOwnerInfos();
    }

    load(id) {
        this.clinicOwnerInfoService.find(id).subscribe((clinicOwnerInfo) => {
            this.clinicOwnerInfo = clinicOwnerInfo;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInClinicOwnerInfos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'clinicOwnerInfoListModification',
            (response) => this.load(this.clinicOwnerInfo.id)
        );
    }
}
