import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'starRating'
})
export class StarRatingPipe implements PipeTransform {
  transform(rating: number): string {
    // Logic to convert numeric rating to star rating representation
    const filledStars = Math.floor(rating);
    const emptyStars = 5 - filledStars;

    return '★'.repeat(filledStars) + '☆'.repeat(emptyStars);
  }
}
