import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MedicineSystem } from './medicine-system.model';
import { MedicineSystemPopupService } from './medicine-system-popup.service';
import { MedicineSystemService } from './medicine-system.service';

@Component({
    selector: 'jhi-medicine-system-delete-dialog',
    templateUrl: './medicine-system-delete-dialog.component.html'
})
export class MedicineSystemDeleteDialogComponent {

    medicineSystem: MedicineSystem;

    constructor(
        private medicineSystemService: MedicineSystemService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.medicineSystemService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'medicineSystemListModification',
                content: 'Deleted an medicineSystem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-medicine-system-delete-popup',
    template: ''
})
export class MedicineSystemDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private medicineSystemPopupService: MedicineSystemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.medicineSystemPopupService
                .open(MedicineSystemDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
