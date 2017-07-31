/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { ClinicLenusTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ReceptionistDetailComponent } from '../../../../../../main/webapp/app/entities/receptionist/receptionist-detail.component';
import { ReceptionistService } from '../../../../../../main/webapp/app/entities/receptionist/receptionist.service';
import { Receptionist } from '../../../../../../main/webapp/app/entities/receptionist/receptionist.model';

describe('Component Tests', () => {

    describe('Receptionist Management Detail Component', () => {
        let comp: ReceptionistDetailComponent;
        let fixture: ComponentFixture<ReceptionistDetailComponent>;
        let service: ReceptionistService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [ClinicLenusTestModule],
                declarations: [ReceptionistDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ReceptionistService,
                    JhiEventManager
                ]
            }).overrideTemplate(ReceptionistDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ReceptionistDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReceptionistService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Receptionist(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.receptionist).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
