import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { ClinicPatientData } from './clinic-patient-data.model';
import { ClinicPatientDataService } from './clinic-patient-data.service';

@Component({
    selector: 'jhi-clinic-patient-data-detail',
    templateUrl: './clinic-patient-data-detail.component.html'
})
export class ClinicPatientDataDetailComponent implements OnInit, OnDestroy {

    clinicPatientData: ClinicPatientData;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private clinicPatientDataService: ClinicPatientDataService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInClinicPatientData();
    }

    load(id) {
        this.clinicPatientDataService.find(id).subscribe((clinicPatientData) => {
            this.clinicPatientData = clinicPatientData;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInClinicPatientData() {
        this.eventSubscriber = this.eventManager.subscribe(
            'clinicPatientDataListModification',
            (response) => this.load(this.clinicPatientData.id)
        );
    }
}
