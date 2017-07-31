import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Drugs } from './drugs.model';
import { DrugsPopupService } from './drugs-popup.service';
import { DrugsService } from './drugs.service';

@Component({
    selector: 'jhi-drugs-delete-dialog',
    templateUrl: './drugs-delete-dialog.component.html'
})
export class DrugsDeleteDialogComponent {

    drugs: Drugs;

    constructor(
        private drugsService: DrugsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.drugsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'drugsListModification',
                content: 'Deleted an drugs'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-drugs-delete-popup',
    template: ''
})
export class DrugsDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private drugsPopupService: DrugsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.drugsPopupService
                .open(DrugsDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
