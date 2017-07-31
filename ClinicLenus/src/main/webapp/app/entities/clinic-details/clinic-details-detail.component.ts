import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager , JhiDataUtils } from 'ng-jhipster';

import { ClinicDetails } from './clinic-details.model';
import { ClinicDetailsService } from './clinic-details.service';

@Component({
    selector: 'jhi-clinic-details-detail',
    templateUrl: './clinic-details-detail.component.html'
})
export class ClinicDetailsDetailComponent implements OnInit, OnDestroy {

    clinicDetails: ClinicDetails;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private clinicDetailsService: ClinicDetailsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInClinicDetails();
    }

    load(id) {
        this.clinicDetailsService.find(id).subscribe((clinicDetails) => {
            this.clinicDetails = clinicDetails;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInClinicDetails() {
        this.eventSubscriber = this.eventManager.subscribe(
            'clinicDetailsListModification',
            (response) => this.load(this.clinicDetails.id)
        );
    }
}
