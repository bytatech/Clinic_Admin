import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { NonGoverntment } from './non-governtment.model';
import { NonGoverntmentPopupService } from './non-governtment-popup.service';
import { NonGoverntmentService } from './non-governtment.service';

@Component({
    selector: 'jhi-non-governtment-delete-dialog',
    templateUrl: './non-governtment-delete-dialog.component.html'
})
export class NonGoverntmentDeleteDialogComponent {

    nonGoverntment: NonGoverntment;

    constructor(
        private nonGoverntmentService: NonGoverntmentService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.nonGoverntmentService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'nonGoverntmentListModification',
                content: 'Deleted an nonGoverntment'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-non-governtment-delete-popup',
    template: ''
})
export class NonGoverntmentDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private nonGoverntmentPopupService: NonGoverntmentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.nonGoverntmentPopupService
                .open(NonGoverntmentDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
