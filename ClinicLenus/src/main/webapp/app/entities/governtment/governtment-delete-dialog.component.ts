import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Governtment } from './governtment.model';
import { GoverntmentPopupService } from './governtment-popup.service';
import { GoverntmentService } from './governtment.service';

@Component({
    selector: 'jhi-governtment-delete-dialog',
    templateUrl: './governtment-delete-dialog.component.html'
})
export class GoverntmentDeleteDialogComponent {

    governtment: Governtment;

    constructor(
        private governtmentService: GoverntmentService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.governtmentService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'governtmentListModification',
                content: 'Deleted an governtment'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-governtment-delete-popup',
    template: ''
})
export class GoverntmentDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private governtmentPopupService: GoverntmentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.governtmentPopupService
                .open(GoverntmentDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
