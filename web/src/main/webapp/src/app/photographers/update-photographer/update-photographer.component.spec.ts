import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePhotographerComponent } from './update-photographer.component';

describe('UpdatePhotographerComponent', () => {
  let component: UpdatePhotographerComponent;
  let fixture: ComponentFixture<UpdatePhotographerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatePhotographerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatePhotographerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
