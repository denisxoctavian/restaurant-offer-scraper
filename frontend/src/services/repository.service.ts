import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Menu } from '../models/menu';
import { environment } from '../environments/environment';



const httpOptions = {
    headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*',
        'Accept': 'application/json',
        'Access-Control-Allow-Headers':
            'Origin, X-Requested-With, Access-Control-Request-Method,  Access-Control-Request-Headers, Content-Type, Accept, Authorization',
        'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})
export class RepositoryService {

    readonly http = inject(HttpClient)

    getAllMenus(): Observable<Menu[]> {
        return this.http.get<Menu[]>(environment.API_PATH + `/menu/all`, { headers: httpOptions.headers })
    }
}
