/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { ClinicLenusTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ClinicDetailsDetailComponent } from '../../../../../../main/webapp/app/entities/clinic-details/clinic-details-detail.component';
import { ClinicDetailsService } from '../../../../../../main/webapp/app/entities/clinic-details/clinic-details.service';
import { ClinicDetails } from '../../../../../../main/webapp/app/entities/clinic-details/clinic-details.model';

describe('Component Tests', () => {

    describe('ClinicDetails Management Detail Component', () => {
        let comp: ClinicDetailsDetailComponent;
        let fixture: ComponentFixture<ClinicDetailsDetailComponent>;
        let service: ClinicDetailsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ClinicLenusTestModule],
                declarations: [ClinicDetailsDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ClinicDetailsService,
                    JhiEventManager
                ]
            }).overrideTemplate(ClinicDetailsDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ClinicDetailsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ClinicDetailsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ClinicDetails(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.clinicDetails).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
