/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { ClinicLenusTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ClinicPatientPrivateDataDetailComponent } from '../../../../../../main/webapp/app/entities/clinic-patient-private-data/clinic-patient-private-data-detail.component';
import { ClinicPatientPrivateDataService } from '../../../../../../main/webapp/app/entities/clinic-patient-private-data/clinic-patient-private-data.service';
import { ClinicPatientPrivateData } from '../../../../../../main/webapp/app/entities/clinic-patient-private-data/clinic-patient-private-data.model';

describe('Component Tests', () => {

    describe('ClinicPatientPrivateData Management Detail Component', () => {
        let comp: ClinicPatientPrivateDataDetailComponent;
        let fixture: ComponentFixture<ClinicPatientPrivateDataDetailComponent>;
        let service: ClinicPatientPrivateDataService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ClinicLenusTestModule],
                declarations: [ClinicPatientPrivateDataDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ClinicPatientPrivateDataService,
                    JhiEventManager
                ]
            }).overrideTemplate(ClinicPatientPrivateDataDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ClinicPatientPrivateDataDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ClinicPatientPrivateDataService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ClinicPatientPrivateData(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.clinicPatientPrivateData).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
