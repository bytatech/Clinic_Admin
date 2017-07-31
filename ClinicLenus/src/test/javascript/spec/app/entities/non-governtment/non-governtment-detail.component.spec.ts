/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { ClinicLenusTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { NonGoverntmentDetailComponent } from '../../../../../../main/webapp/app/entities/non-governtment/non-governtment-detail.component';
import { NonGoverntmentService } from '../../../../../../main/webapp/app/entities/non-governtment/non-governtment.service';
import { NonGoverntment } from '../../../../../../main/webapp/app/entities/non-governtment/non-governtment.model';

describe('Component Tests', () => {

    describe('NonGoverntment Management Detail Component', () => {
        let comp: NonGoverntmentDetailComponent;
        let fixture: ComponentFixture<NonGoverntmentDetailComponent>;
        let service: NonGoverntmentService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ClinicLenusTestModule],
                declarations: [NonGoverntmentDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    NonGoverntmentService,
                    JhiEventManager
                ]
            }).overrideTemplate(NonGoverntmentDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NonGoverntmentDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NonGoverntmentService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new NonGoverntment(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.nonGoverntment).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
