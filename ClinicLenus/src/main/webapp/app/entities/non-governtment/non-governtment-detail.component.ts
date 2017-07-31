import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { NonGoverntment } from './non-governtment.model';
import { NonGoverntmentService } from './non-governtment.service';

@Component({
    selector: 'jhi-non-governtment-detail',
    templateUrl: './non-governtment-detail.component.html'
})
export class NonGoverntmentDetailComponent implements OnInit, OnDestroy {

    nonGoverntment: NonGoverntment;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private nonGoverntmentService: NonGoverntmentService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInNonGoverntments();
    }

    load(id) {
        this.nonGoverntmentService.find(id).subscribe((nonGoverntment) => {
            this.nonGoverntment = nonGoverntment;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInNonGoverntments() {
        this.eventSubscriber = this.eventManager.subscribe(
            'nonGoverntmentListModification',
            (response) => this.load(this.nonGoverntment.id)
        );
    }
}
