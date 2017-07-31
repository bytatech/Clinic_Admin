import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Governtment } from './governtment.model';
import { GoverntmentService } from './governtment.service';

@Component({
    selector: 'jhi-governtment-detail',
    templateUrl: './governtment-detail.component.html'
})
export class GoverntmentDetailComponent implements OnInit, OnDestroy {

    governtment: Governtment;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private governtmentService: GoverntmentService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInGoverntments();
    }

    load(id) {
        this.governtmentService.find(id).subscribe((governtment) => {
            this.governtment = governtment;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInGoverntments() {
        this.eventSubscriber = this.eventManager.subscribe(
            'governtmentListModification',
            (response) => this.load(this.governtment.id)
        );
    }
}
