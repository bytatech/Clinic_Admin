import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Receptionist } from './receptionist.model';
import { ReceptionistService } from './receptionist.service';

@Component({
    selector: 'jhi-receptionist-detail',
    templateUrl: './receptionist-detail.component.html'
})
export class ReceptionistDetailComponent implements OnInit, OnDestroy {

    receptionist: Receptionist;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private receptionistService: ReceptionistService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInReceptionists();
    }

    load(id) {
        this.receptionistService.find(id).subscribe((receptionist) => {
            this.receptionist = receptionist;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInReceptionists() {
        this.eventSubscriber = this.eventManager.subscribe(
            'receptionistListModification',
            (response) => this.load(this.receptionist.id)
        );
    }
}
