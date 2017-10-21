import { InMemoryDbService } from 'angular-in-memory-web-api';
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    let dbs = [
      {name: 'dbm', count: 12},
      {name: 'abc', count: 123},
      {name: 'def', count: 2},
      {name: 'gah', count: 1},
      
    ];
    return {dbs};
  }
}


/*
Copyright 2016 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/