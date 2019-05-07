import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Disc } from '../model/disc';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class DiscService {

	private discsUrls: string;

	constructor(private http: HttpClient) {
		this.discsUrls = 'http://localhost:8080/api/disc';
	}

	public findAll(): Observable<Disc[]> {
		let headers: HttpHeaders = new HttpHeaders({
			'Authorization': 'Basic ' + sessionStorage.getItem('token')
		});
		let options = { headers: headers };
		return this.http.get<Disc[]>(this.discsUrls, options);
	}

	public save(disc: Disc) {
		let headers: HttpHeaders = new HttpHeaders({
			'Authorization': 'Basic ' + sessionStorage.getItem('token')
		});
		let options = { headers: headers };
		return this.http.post<Disc>(this.discsUrls, disc, options);
	}

	public remove(id: number) {
		let headers: HttpHeaders = new HttpHeaders({
			'Authorization': 'Basic ' + sessionStorage.getItem('token')
		});
		let options = { headers: headers };
		return this.http.delete(this.discsUrls + '/' + id, options);
	}
}
