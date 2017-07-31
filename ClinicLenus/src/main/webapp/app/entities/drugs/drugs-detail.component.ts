import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { Drugs } from './drugs.model';
import { DrugsService } from './drugs.service';

@Component({
    selector: 'jhi-drugs-detail',
    templateUrl: './drugs-detail.component.html'
})
export class DrugsDetailComponent implements OnInit, OnDestroy {

    drugs: Drugs;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private drugsService: DrugsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDrugs();
    }

    load(id) {
        this.drugsService.find(id).subscribe((drugs) => {
            this.drugs = drugs;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDrugs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'drugsListModification',
            (response) => this.load(this.drugs.id)
        );
    }
}
