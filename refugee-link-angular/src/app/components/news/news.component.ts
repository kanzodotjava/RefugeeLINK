import { Component } from '@angular/core';
import { NewsService } from '../../services/newsApi/news.service';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css'],
})
export class NewsComponent {
  newsData: any[] = []; // Initialize with an empty array

  constructor(private newsApiService: NewsService) {}

  ngOnInit(): void {
    this.fetchNews();
  }

  fetchNews() {
    this.newsApiService.getNews().subscribe((data: any) => {
      // Assuming API returns an array of articles
      this.newsData = data.articles.slice(0, 6); // Get the first 6 articles
    });
  }
}
