import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { MedicineSystem } from './medicine-system.model';
import { MedicineSystemService } from './medicine-system.service';

@Injectable()
export class MedicineSystemPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private medicineSystemService: MedicineSystemService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.medicineSystemService.find(id).subscribe((medicineSystem) => {
                this.medicineSystemModalRef(component, medicineSystem);
            });
        } else {
            return this.medicineSystemModalRef(component, new MedicineSystem());
        }
    }

    medicineSystemModalRef(component: Component, medicineSystem: MedicineSystem): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.medicineSystem = medicineSystem;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
