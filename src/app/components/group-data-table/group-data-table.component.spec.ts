import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupDataTableComponent } from './group-data-table.component';

describe('GroupDataTableComponent', () => {
  let component: GroupDataTableComponent;
  let fixture: ComponentFixture<GroupDataTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupDataTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupDataTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
