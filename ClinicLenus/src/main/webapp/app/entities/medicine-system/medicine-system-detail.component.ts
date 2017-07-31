import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { MedicineSystem } from './medicine-system.model';
import { MedicineSystemService } from './medicine-system.service';

@Component({
    selector: 'jhi-medicine-system-detail',
    templateUrl: './medicine-system-detail.component.html'
})
export class MedicineSystemDetailComponent implements OnInit, OnDestroy {

    medicineSystem: MedicineSystem;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private medicineSystemService: MedicineSystemService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMedicineSystems();
    }

    load(id) {
        this.medicineSystemService.find(id).subscribe((medicineSystem) => {
            this.medicineSystem = medicineSystem;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMedicineSystems() {
        this.eventSubscriber = this.eventManager.subscribe(
            'medicineSystemListModification',
            (response) => this.load(this.medicineSystem.id)
        );
    }
}
