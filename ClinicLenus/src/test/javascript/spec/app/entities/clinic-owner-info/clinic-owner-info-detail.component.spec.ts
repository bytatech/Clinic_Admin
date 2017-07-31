/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { ClinicLenusTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ClinicOwnerInfoDetailComponent } from '../../../../../../main/webapp/app/entities/clinic-owner-info/clinic-owner-info-detail.component';
import { ClinicOwnerInfoService } from '../../../../../../main/webapp/app/entities/clinic-owner-info/clinic-owner-info.service';
import { ClinicOwnerInfo } from '../../../../../../main/webapp/app/entities/clinic-owner-info/clinic-owner-info.model';

describe('Component Tests', () => {

    describe('ClinicOwnerInfo Management Detail Component', () => {
        let comp: ClinicOwnerInfoDetailComponent;
        let fixture: ComponentFixture<ClinicOwnerInfoDetailComponent>;
        let service: ClinicOwnerInfoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ClinicLenusTestModule],
                declarations: [ClinicOwnerInfoDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ClinicOwnerInfoService,
                    JhiEventManager
                ]
            }).overrideTemplate(ClinicOwnerInfoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ClinicOwnerInfoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ClinicOwnerInfoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new ClinicOwnerInfo(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.clinicOwnerInfo).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
