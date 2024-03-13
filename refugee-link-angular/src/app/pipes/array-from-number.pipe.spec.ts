import { ArrayFromNumberPipe } from './array-from-number.pipe';

describe('ArrayFromNumberPipe', () => {
  it('create an instance', () => {
    const pipe = new ArrayFromNumberPipe();
    expect(pipe).toBeTruthy();
  });
});
