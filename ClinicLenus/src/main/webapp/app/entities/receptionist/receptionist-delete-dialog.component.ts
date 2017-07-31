import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Receptionist } from './receptionist.model';
import { ReceptionistPopupService } from './receptionist-popup.service';
import { ReceptionistService } from './receptionist.service';

@Component({
    selector: 'jhi-receptionist-delete-dialog',
    templateUrl: './receptionist-delete-dialog.component.html'
})
export class ReceptionistDeleteDialogComponent {

    receptionist: Receptionist;

    constructor(
        private receptionistService: ReceptionistService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.receptionistService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'receptionistListModification',
                content: 'Deleted an receptionist'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-receptionist-delete-popup',
    template: ''
})
export class ReceptionistDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private receptionistPopupService: ReceptionistPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.receptionistPopupService
                .open(ReceptionistDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
