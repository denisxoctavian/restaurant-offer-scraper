import { Component, OnInit, ViewChild, inject } from '@angular/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTable, MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { Sort, MatSortModule, MatSort } from '@angular/material/sort';
import { Menu } from '../../models/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator } from '@angular/material/paginator';
import { RepositoryService } from '../../services/repository.service';
import { SelectionModel } from '@angular/cdk/collections';
import { MatTooltipModule } from '@angular/material/tooltip';


export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}



@Component({
  selector: 'app-table',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatTableModule, MatSortModule, MatPaginator, MatButtonModule, MatIconModule, MatTooltipModule],
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss'
})

export class TableComponent implements OnInit {

  private repository = inject(RepositoryService);

  @ViewChild(MatTable) table!: MatTable<Menu>;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  displayedColumns = ['name', 'price', 'picture', 'restaurantId', 'date'];
  dataSource: MatTableDataSource<Menu>;
  selection = new SelectionModel<Menu>(true, []);

  constructor() {
    this.dataSource = new MatTableDataSource<Menu>;
  }

  ngOnInit(): void {
    this.allMenus();
  }

  allMenus() {
    this.repository.getAllMenus().subscribe((result: any) => {
      this.dataSource.data = result
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });

  }

  openSelection(row: any) { }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
