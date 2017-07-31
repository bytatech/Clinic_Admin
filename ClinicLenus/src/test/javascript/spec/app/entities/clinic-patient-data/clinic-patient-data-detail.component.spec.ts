/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { ClinicLenusTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ClinicPatientDataDetailComponent } from '../../../../../../main/webapp/app/entities/clinic-patient-data/clinic-patient-data-detail.component';
import { ClinicPatientDataService } from '../../../../../../main/webapp/app/entities/clinic-patient-data/clinic-patient-data.service';
import { ClinicPatientData } from '../../../../../../main/webapp/app/entities/clinic-patient-data/clinic-patient-data.model';

describe('Component Tests', () => {

    describe('ClinicPatientData Management Detail Component', () => {
        let comp: ClinicPatientDataDetailComponent;
        let fixture: ComponentFixture<ClinicPatientDataDetailComponent>;
        let service: ClinicPatientDataService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ClinicLenusTestModule],
                declarations: [ClinicPatientDataDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ClinicPatientDataService,
                    JhiEventManager
                ]
            }).overrideTemplate(ClinicPatientDataDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ClinicPatientDataDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ClinicPatientDataService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ClinicPatientData(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.clinicPatientData).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
