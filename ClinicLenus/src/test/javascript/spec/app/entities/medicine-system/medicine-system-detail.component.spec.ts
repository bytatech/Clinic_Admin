/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { ClinicLenusTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { MedicineSystemDetailComponent } from '../../../../../../main/webapp/app/entities/medicine-system/medicine-system-detail.component';
import { MedicineSystemService } from '../../../../../../main/webapp/app/entities/medicine-system/medicine-system.service';
import { MedicineSystem } from '../../../../../../main/webapp/app/entities/medicine-system/medicine-system.model';

describe('Component Tests', () => {

    describe('MedicineSystem Management Detail Component', () => {
        let comp: MedicineSystemDetailComponent;
        let fixture: ComponentFixture<MedicineSystemDetailComponent>;
        let service: MedicineSystemService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ClinicLenusTestModule],
                declarations: [MedicineSystemDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    MedicineSystemService,
                    JhiEventManager
                ]
            }).overrideTemplate(MedicineSystemDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MedicineSystemDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MedicineSystemService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new MedicineSystem(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.medicineSystem).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
