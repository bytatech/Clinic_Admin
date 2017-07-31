import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { ClinicPatientPrivateData } from './clinic-patient-private-data.model';
import { ClinicPatientPrivateDataService } from './clinic-patient-private-data.service';

@Component({
    selector: 'jhi-clinic-patient-private-data-detail',
    templateUrl: './clinic-patient-private-data-detail.component.html'
})
export class ClinicPatientPrivateDataDetailComponent implements OnInit, OnDestroy {

    clinicPatientPrivateData: ClinicPatientPrivateData;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private clinicPatientPrivateDataService: ClinicPatientPrivateDataService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInClinicPatientPrivateData();
    }

    load(id) {
        this.clinicPatientPrivateDataService.find(id).subscribe((clinicPatientPrivateData) => {
            this.clinicPatientPrivateData = clinicPatientPrivateData;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInClinicPatientPrivateData() {
        this.eventSubscriber = this.eventManager.subscribe(
            'clinicPatientPrivateDataListModification',
            (response) => this.load(this.clinicPatientPrivateData.id)
        );
    }
}
