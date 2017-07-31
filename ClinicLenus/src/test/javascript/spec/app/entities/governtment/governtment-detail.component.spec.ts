/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { ClinicLenusTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { GoverntmentDetailComponent } from '../../../../../../main/webapp/app/entities/governtment/governtment-detail.component';
import { GoverntmentService } from '../../../../../../main/webapp/app/entities/governtment/governtment.service';
import { Governtment } from '../../../../../../main/webapp/app/entities/governtment/governtment.model';

describe('Component Tests', () => {

    describe('Governtment Management Detail Component', () => {
        let comp: GoverntmentDetailComponent;
        let fixture: ComponentFixture<GoverntmentDetailComponent>;
        let service: GoverntmentService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ClinicLenusTestModule],
                declarations: [GoverntmentDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    GoverntmentService,
                    JhiEventManager
                ]
            }).overrideTemplate(GoverntmentDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GoverntmentDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GoverntmentService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Governtment(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.governtment).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
